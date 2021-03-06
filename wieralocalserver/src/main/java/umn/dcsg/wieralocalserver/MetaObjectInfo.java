package umn.dcsg.wieralocalserver;

import java.util.*;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

/**
 * Created with IntelliJ IDEA. User: ajay Date: 29/03/13 Time: 12:42 PM To
 * change this template use File | Settings | File Templates.
 * 
 * This tracks a single object created and moved around LocalInstance.
 */
//Store Key and data location with version. -- ks

@Entity
public class MetaObjectInfo {
    @PrimaryKey
    String m_key = null;

    @SecondaryKey(relate = Relationship.MANY_TO_MANY)
    Set<String> tags = null;

    @SecondaryKey(relate = Relationship.MANY_TO_MANY)
    Set<Long> accessTime = null;

    public static final int LATEST_VERSION = 99999999;
    public static final int NEW_VERSION = 0;
    public static final int NO_SUCH_VERSION = -1;
    public static final int NO_VERSIONING_SUPPORT = -2;

    MetaVerInfo m_latestMetaInfo;
    boolean m_pin = false;
    boolean m_bSupportVersioning = false;
    HashMap<Integer, MetaVerInfo> m_versions;

    public MetaObjectInfo() {
        this("", System.currentTimeMillis(), 0, "", false, NO_VERSIONING_SUPPORT);
    }

    MetaObjectInfo(String key, long lSize, String strTag, int nVer) {
        this(key, System.currentTimeMillis(), lSize, strTag, false, nVer);
    }

    MetaObjectInfo(String strKey, long lStartTime, long lSize, String strTag, boolean bPin, int nVer) {
        //For each key.
        m_key = strKey;

        //Todo Need to be improved
        m_bSupportVersioning = nVer != NO_VERSIONING_SUPPORT;

        if(m_bSupportVersioning == false) {
            nVer = NEW_VERSION;
        }

        //Object Info
        MetaVerInfo meta = new MetaVerInfo(nVer, System.currentTimeMillis(), lSize);
        meta.m_lAccessCnt = 0;
        meta.m_lCreatedTime = lStartTime;
        meta.m_lLastAccessTime = lStartTime;
        meta.m_lLastModifiedTime = 0;
        meta.m_bDirty = false;
        meta.m_bPin = bPin;
        meta.m_lSize = lSize;

        if (m_bSupportVersioning == true) {
            /*//Init version
            m_nLatestVer = NEW_VERSION;*/
            m_versions = new HashMap<>();
            m_versions.put(nVer, meta);
        } /*else {
            m_nLatestVer = NO_VERSIONING_SUPPORT;
        }*/

        m_latestMetaInfo = meta;

        tags = new HashSet<String>();
        addTag(strTag);

        accessTime = new HashSet<Long>();
        accessTime.add(lStartTime);
    }

    //Insert new version. always increased.
    synchronized public void addNewVersion(long lStartTime, long lSize) {
        if (m_bSupportVersioning == false) {
            //Version is not supported
            return;
        }

        int nVer = m_latestMetaInfo.getVersion() + 1;
        MetaVerInfo meta = new MetaVerInfo(nVer, lStartTime, lSize);
        m_versions.put(nVer, meta);
        m_latestMetaInfo = meta;
    }

    //Insert new version. always increased.
    synchronized public void addNewVersion(long lStartTime, long lSize, int nVer) {
        if (m_bSupportVersioning == false) {
            //Version is not supported
            return;
        }

        MetaVerInfo meta = new MetaVerInfo(nVer, lStartTime, lSize);
        m_versions.put(nVer, meta);

        if(nVer >= m_latestMetaInfo.getVersion()) {
            m_latestMetaInfo = meta;
        }
    }

    //Update version if it is updated by other peers, or newly added,
    synchronized public void updateVersion(int nVer, long lModifiedTime, long lSize) {
        if (m_bSupportVersioning == false) {
            //Version is not supported
            return;
        }

        MetaVerInfo meta = m_versions.get(nVer);

        if (meta == null) {
            meta = new MetaVerInfo(nVer, lModifiedTime, lSize);
            m_versions.put(nVer, meta);

            //Need to be synced?
            if (m_latestMetaInfo.getVersion() < nVer) {
                m_latestMetaInfo = meta;
            }
        } else {
            meta.setLastModifedTime(lModifiedTime);
            meta.setSize(lSize);
        }
    }

    //Update version if it is updated by other peers, or newly added,
    synchronized public boolean containsVer(int nVer) {
        return m_versions.containsKey(nVer);
    }

    public int getLastestVersion() {
        return m_latestMetaInfo.getVersion();
    }

    public String getKey() {
        return m_key;
    }

    public String getVersionedKey() {
        return getVersionedKey(m_latestMetaInfo.getVersion());
    }

    public String getVersionedKey(int nVer) {
        if (m_bSupportVersioning == true) {
            //Thread.dumpStack();
            return m_key + String.format("_ver_%d", nVer);
        } else {
            return m_key;
        }
    }

    /*public void fillVersionGap(long version) {
        long nGap = version - m_nLatestVer;

        for (int i = 0; i < nGap; i++) {
            m_nLatestVer++;
            m_versions.put(m_nLatestVer, null);
        }
    }*/

    public HashMap<Integer, MetaVerInfo> getVersionList() {
        if (m_bSupportVersioning == true) {
            return m_versions;
        } else {
            HashMap<Integer, MetaVerInfo> ver = new HashMap<Integer, MetaVerInfo>();
            ver.put(0, m_latestMetaInfo);
            return ver;
        }
    }

    /*public boolean addLocalLocale(long lVer, String strTierName, TierInfo.TIER_TYPE tierType) {
        return addLocale(lVer, LocalServer.getHostName(), strTierName, tierType);
    }*/

    /*public boolean addLocalLocale(String strTierName, TierInfo.TIER_TYPE tierType) {
        return addLocale(m_nLatestVer, LocalServer.getHostName(), strTierName, tierType);
    }*/

    public boolean addLocale(int nVer, Locale locale) {
        return addLocale(nVer, locale.getHostName(), locale.getTierName(), locale.getTierType());
    }

    public boolean addLocale(String strHostName, String strTierName, TierInfo.TIER_TYPE tierType) {
        return addLocale(getLastestVersion(), strHostName, strTierName, tierType);
    }

    //Only when called for internal usage like move or replicate
    //Tier Name is unique.
    public boolean addLocale(int nVer, String strHostName, String strTierName, TierInfo.TIER_TYPE tierType) {
        MetaVerInfo ver;

        if (m_bSupportVersioning == true) {
            ver = m_versions.get(nVer); //findByVer(nVer);
        } else {
            ver = m_latestMetaInfo;
        }

        if (ver != null) {
            return ver.addLocale(strHostName, strTierName, tierType);
        } else {
            return false;
        }
    }

    public synchronized boolean setSize(long lSize) {
        return setSize(LATEST_VERSION, lSize);
    }

    public synchronized boolean setSize(long nVer, long lSize) {
        MetaVerInfo info;

        if (m_bSupportVersioning == true) {
            info = m_versions.get(nVer); //findByVer(nVer);
        } else {
            info = m_latestMetaInfo;
        }

        if (info != null) {
            info.setSize(lSize);
            return true;
        }

        return false;
    }

    public synchronized long getSize() {
        return getSize(LATEST_VERSION);
    }

    public synchronized long getSize(long nVer) {
        MetaVerInfo info;

        if (m_bSupportVersioning == true) {
            info = m_versions.get(nVer); //findByVer(nVer);
        } else {
            info = m_latestMetaInfo;
        }

        if (info != null) {
            return info.getSize();
        }

        return NO_SUCH_VERSION;
    }

    public synchronized boolean removeLocale(String strHostName, String strTierName) {
        return removeLocale(getLastestVersion(), strHostName, strTierName);
    }

    public synchronized boolean removeLocale(long nVer, Locale targetLocale) {
        return removeLocale(nVer, targetLocale.getHostName(), targetLocale.getTierName());
    }

    public synchronized boolean removeLocale(long nVer, String strHostName, String strTierName) {
        MetaVerInfo info;

        if (m_bSupportVersioning == true) {
            info = m_versions.get(nVer); //findByVer(nVer);
        } else {
            info = m_latestMetaInfo;
        }

        if (info != null) {
            return info.removeLocale(strHostName, strTierName);
        } else {
            return false;
        }
    }

    public synchronized boolean removeVersion(long nVer) {
        if (m_bSupportVersioning == true && m_versions.containsKey(nVer) == true) {
            m_versions.remove(nVer);
            return true;
        }

        return false;
    }

    // set last access time to current time
    public synchronized void setLAT() {
        setLAT(System.currentTimeMillis());
    }

    // set the last access time
    public synchronized void setLAT(long lLAT) {
        //Last access time has been updated
        //Update db also
        accessTime.remove(m_latestMetaInfo.m_lLastAccessTime);
        m_latestMetaInfo.m_lLastAccessTime = lLAT;
        accessTime.add(m_latestMetaInfo.m_lLastAccessTime);
    }

    // set the modifeid time
    public synchronized void setLastModifiedTime(long time) {
        m_latestMetaInfo.m_lLastModifiedTime = time;
    }

    public synchronized long getLastModifiedTime() {
        return m_latestMetaInfo.m_lLastModifiedTime;
    }

    // set the access count
    public synchronized void set_accessCount(int count) {
        m_latestMetaInfo.m_lAccessCnt = count;
    }

    public synchronized long getLAT() {
        return m_latestMetaInfo.m_lLastAccessTime;
    }

    // increment the access count
    public synchronized void countInc() {
        m_latestMetaInfo.m_lAccessCnt++;
    }

    public synchronized long get_accessCount() {
        return m_latestMetaInfo.m_lAccessCnt;
    }

    public synchronized void setPin() {
        m_pin = true;
    }

    public synchronized void unsetPin() {
        m_pin = false;
    }

    public synchronized boolean isPinned() {
        return m_pin;
    }

    public synchronized Locale getLocale(boolean bOnlyLocal) {
        return getLocale(getLastestVersion(), bOnlyLocal);
    }

    public synchronized Locale getLocale(int nVer, boolean bOnlyLocal) {
        MetaVerInfo info;

        if (m_bSupportVersioning == true) {
            if (nVer < 0) {
                nVer = getLastestVersion();
            }

            info = m_versions.get(nVer);

            if (info == null) {
                return null;
            }
        } else {
            info = m_latestMetaInfo;
        }

        return info.getFastestLocale(bOnlyLocal);
    }

    public synchronized Locale getLocale(int nVer, String strLocaleID){
        String[] tokens = strLocaleID.split(":");
        return getLocale(nVer, tokens[0], tokens[1]);
    }

    public synchronized Locale getLocale(int nVer, String strHostName, String strTierName){
        MetaVerInfo info;
        if(m_bSupportVersioning == true){
            info = m_versions.get(nVer);
        }else{
            info = m_latestMetaInfo;
        }
        if(info == null){
            return null;
        }
        return info.getLocale(strHostName, strTierName);
    }

    public synchronized boolean hasLocale(String strLocaleID) {
        String[] tokens = strLocaleID.split(":");
        return hasLocale(getLastestVersion(), tokens[0], tokens[1]);
    }

    public synchronized boolean hasLocale(int nVer, String strLocaleID) {
        String[] tokens = strLocaleID.split(":");
        return hasLocale(nVer, tokens[0], tokens[1]);
    }

    public synchronized boolean hasLocale(int nVer, String strHostName, String strTierName) {
        MetaVerInfo info;

        if (strHostName == null) {
            strHostName = LocalServer.getHostName();
        }

        if (m_bSupportVersioning == true) {
            if (nVer < 0) {
                nVer = getLastestVersion();
            }

            info = m_versions.get(nVer);//findByVer(nVer);
        } else {
            info = m_latestMetaInfo;
        }

        if (info != null) {
            return info.hasLocale(strHostName, strTierName);
        }

        return false;
    }

    public synchronized Map<String, Locale> getLocaleList() {
        return getLocaleList(getLastestVersion());
    }

    public synchronized Map<String, Locale> getLocaleList(int nVer) {
        MetaVerInfo info;

        if (m_bSupportVersioning == true) {
            if (nVer < 0) {
                nVer = getLastestVersion();
            }

            info = m_versions.get(nVer);
        } else {
            info = m_latestMetaInfo;
        }

        return info.getLocaleList();
    }

    public synchronized void setDirty() {
        m_latestMetaInfo.m_bDirty = true;
    }

    public synchronized void clearDirty() {
        m_latestMetaInfo.m_bDirty = false;
    }

    public synchronized boolean isDirty() {
        return m_latestMetaInfo.m_bDirty;
    }

    public synchronized Map<String, Object> getMetaData() {
        return getMetaData(LATEST_VERSION);
    }

    public synchronized Map<String, Object> getMetaData(int nVer) {
        if(nVer == LATEST_VERSION) {
            return m_latestMetaInfo.getMetaInfo();
        } else {
            MetaVerInfo meta = m_versions.get(nVer);

            if(meta != null) {
                return meta.getMetaInfo();
            } else {
                return null;
            }
        }
    }

    public void addTag (String strTag) {
        tags.add(strTag);
    }

    public Set getTags () {
        return tags;
    }
}