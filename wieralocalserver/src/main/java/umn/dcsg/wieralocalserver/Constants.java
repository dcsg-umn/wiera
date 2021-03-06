package umn.dcsg.wieralocalserver;

/**
 * Created with IntelliJ IDEA.
 * User: ajay
 * Date: 1/8/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static final int WIERA_PORT_FOR_LOCAL_SERVER = 55559;
    public static final int WIERA_PORT_FOR_APPLICATIONS = 55558;
    public static final String NULL_VALUE = "nUlLvAlUe";
    public static final String MSGSTART = "[";
    public static final String MSGEND = "]";
    public static final String MSGPAIRSEPARATOR = ":";
    public static final String MSGITEMSEPARATOR = "$$$";
    public static final String COMMANDNUM = "1:";
    public static final String LEVELNUM = "2:";
    public static final String KEYNUM = "3:";
    public static final String VALUENUM = "4:";
    public static final String TAGNUM = "5:";
    public static final String PRIORITYNUM = "6:";
    public static final String LIFETIMENUM = "7:";
    public static final String DONOTVERWRITENUM = "8:";
    public static final String PINDOWNNUM = "9:";
    public static final String CACHEHIT = "1";
    public static final String CACHEMISS = "0";
    public static final String ENDOFMSG = "$$$EOM$$$";

    public static final String GETCOMMAND = "GET";
    public static final String PUTCOMMAND = "PUT";
    public static final String LISTEVENTCOMMAND = "LEVT";
    public static final String PUTEVENTCOMMAND = "PEVT";
    public static final String DELEVENTCOMMAND = "DEVT";

    public static final String TYPE = "type";
    public static final String OP_TIME = "op_time";
    public static final String OP_TYPE = "op_type";

    public static final String VALUE = "value";
    public static final String VALUE2 = "value2";
    public static final String VALUE3 = "value3";
    public static final String VALUE4 = "value4";
    public static final String VALUE5 = "value5";
    public static final String RESULT = "result";

    public static final String KEY = "key";
    public static final String KEY_LIST = "key_list";
    public static final String META_NOT_COMMITED_LIST = "meta_not_update_list";
    public static final String VERSION = "version";
    public static final String SIZE = "size";
    public static final String TIER_NAME = "tier_name";
    public static final String TIER_TYPE = "tier_type";
    public static final String TIER_SIZE = "tier_size";
    public static final String TIER_LIST = "tier_list";
    public static final String TIER_LOCATION = "tier_location";
    public static final String TIER_EXPECTED_LATENCY = "tier_expected_latency";
    public static final String TAG = "tag";
    public static final String CREATED_TIME = "created_time";
    public static final String LAST_ACCESSED_TIME = "last_accessed_time";
    public static final String LAST_MODIFIED_TIME = "last_modified_time";
    public static final String HOSTNAME = "hostname";
    public static final String HOST_LIST = "host_list";
    public static final String LOCAL_INSTANCES = "local_instances";
    public static final String STORAGE_TIERS = "storage_tiers";
    public static final String ONLY_META_INFO = "only_meta_info";
    public static final String SHARE_META_INFO = "share_meta_info";

    public static final String LEADER_HOSTNAME = "leader_hostname";

    //Policy text
    public static final String ID = "id";
    public static final String CONFIGURATIONS = "configurations";
    public static final String VERSION_SUPPORT = "version_support";
    public static final String DESC = "desc";
    public static final String PRIMARY = "primary";
    public static final String PERIOD = "period";
    public static final String MONITORING = "monitoring";
    public static final String QUORUM = "quorum";
    public static final String WRITE_QUORUM = "write_quorum";
    public static final String READ_QUORUM = "read_quorum";

    //public static final String WRITE_QUORUM = "write_quorum";
    public static final String IS_WRITE = "is_write";

    public static final String STORAGE_PROVIDER = "storage_provider";
    public static final String STORAGE_ARG1 = "storage_arg1";
    public static final String STORAGE_ARG2 = "storage_arg2";
    public static final String STORAGE_ID1 = "storage_id1";
    public static final String STORAGE_ID2 = "storage_id2";

    public static final String IP_ADDRESS = "ip";
    public static final String APPLICATION_PORT = "application_port";
    public static final String LOCAL_SERVER_PORT = "local_server_port";
    public static final String PEER_PORT = "peer_port";
    public static final String INSTANCE_PORT = "instance_port";
    public static final String INSTANCE_CNT = "instance_cnt";

    public static final String DATA_DISTRIBUTION = "data_distribution";
    public static final String FAULT_TOLERANCE = "fault_tolerance";
    public static final String DATA_DISTRIBUTION_CONSISTENCY = "data_distribution_consistency";    //Only for Trips

    public static final String WIERA_ID = "wiera_id";
    public static final String WIERA_IPADDRESS = "wiera_ip";
    public static final String WIERA_PORT = "wiera_port";

    public static final String DATA_PLACEMENT = "data_placement";
    public static final String ACCESS_RULE = "access_rule";
    public static final String PREFER_STORAGE_TYPE = "prefer_storage_type";

    public static final String GET_LATENCY = "get_latency";
    public static final String PUT_LATENCY = "put_latency";
    public static final String NETWORK_LATENCY = "network_latency";
    public static final String STORAGE_LATENCY = "storage_latency";
    public static final String OPERATION_LATENCY = "operation_latency";

    public static final String FREE_SPACE = "free_space";
    public static final String NETWORK_COST = "network_cost";
    public static final String STORAGE_COST = "storage_cost";

    public static final String GET_REQUEST_COST = "get_request_cost";
    public static final String PUT_REQUEST_COST = "put_request_cost";
    public static final String DEL_REQUEST_COST = "del_request_cost";
    public static final String DATA_RETRIEVAL = "data_retrieval";
    public static final String DATA_WRITE = "data_write";

    public static final String ACCESS_CNT = "access_cnt";
    public static final String GET_ACCESS_CNT = "get_access_cnt";
    public static final String PUT_ACCESS_CNT = "put_access_cnt";
    public static final String QUERY = "query";
    public static final String QUERY_TYPE = "query_type";
    public static final String ACCESS_INFO = "access_info";
    public static final String OBJECT_SIZE = "object_size";

    //Adaptively choosing strage
    public static final String FASTEST = "fastest";
    public static final String CHEAPEST = "cheapest";


    public static final String NETWORK_PERCENTILE = "network_pct";
    public static final String STORAGE_PERCENTILE = "storage_pct";
    public static final String GET_SLA = "get_sla";
    public static final String PUT_SLA = "put_sla";

    //Support applications APIs
    public static final String GET = "get";
    public static final String PUT = "put";
    public static final String UPDATE = "update";
    public static final String GET_VERSION = "getVersion";
    public static final String GET_VERSION_LIST = "getVersionList";
    public static final String GET_META_DATA = "getMetaData";
    public static final String RENAME = "rename";
    public static final String COPY = "copy";
    public static final String REMOVE = "remove";
    public static final String REMOVE_VERSION = "removeVersion";

    //Support peer Thrifts call
    public static final String PUT_PEER = "put";
    public static final String GET_LASTEST_VERSION_PEER = "getLastestVersion";

    public static final long GB_TO_BYTE = 1000000000;
    public static final long DATA_SIZE = 8192; //135768
    //public static final long	DATA_SIZE = 1638432768;
    //public static final long	DATA_SIZE = 135768;

    //For redis wrapper class and
    public static final String OK = "ok";
    public static final String PING = "ping";

    //Supported Events
    public static final String EVENT_PACKAGE_PATH = "umn.dcsg.wieralocalserver.events.";
    public static final String EVENT_FOR_CLASS = "Event";
    public static final String EVENTS = "events";
    public static final String EVENT_TYPE = "event_type";
    public static final String EVENT_TRIGGER = "event_trigger";
    public static final String EVENT_CONDITIONS = "event_conditions";
    public static final String TIMER_EVENT = "Timer";
    public static final String ACTION_PUT_EVENT = "ActionPut";
    public static final String ACTION_GET_EVENT = "ActionGet";
    public static final String MONITORING_LATENCY_EVENT = "MonitoringLatency";
    public static final String MONITORING_REQUEST_EVENT = "MonitoringRequest";
    public static final String MONITORING_COLD_DATA_EVENT = "MonitoringColdData";
    public static final String MONITORING_TIER_CAPACITY_EVENT = "MonitoringTierCapacity";

    //Supported Responses
    public static final String RESPONSE_PACKAGE_PATH = "umn.dcsg.wieralocalserver.responses.";
    public static final String RESPONSE_FOR_CLASS = "Response";
    public static final String RESPONSES = "responses";
    public static final String RESPONSE_TYPE = "response_type";
    public static final String RESPONSE_PARAMETERS = "response_parameters";
    public static final String STORE_RESPONSE = "Store";
    public static final String RETRIEVE_RESPONSE = "Retrieve";
    public static final String COPY_RESPONSE = "Copy";
    public static final String MOVE_RESPONSE = "Move";
    public static final String DELETE_RESPONSE = "Delete";
    public static final String ENCRYPT_RESPONSE = "Encrypt";
    public static final String DECRYPT_RESPONSE = "Decrypt";
    public static final String COMPRESS_RESPONSE = "Compress";
    public static final String UNCOMPRESS_RESPONSE = "UnCompress";
    public static final String GROW_RESPONSE = "Grow";
    public static final String SHRINK_RESPONSE = "Shrink";

    public static final String WRAPPER_PACKAGE_PATH = "umn.dcsg.wieralocalserver.wrapper.";
    public static final String WRAPPER_FOR_CLASS = "WrapperApplicationInterface";

    //to peers
    public static final String BROADCAST_RESPONSE = "peers.Broadcast";
    public static final String QUEUE_RESPONSE = "peers.Queue";
    public static final String FIND_LATEST_VERSION_NUMBER = "peers.FindLatestVersionNumber";
    public static final String FIND_LOCALES  = "peers.FindLocales";
    public static final String FORWARD_GET_RESPONSE = "peers.ForwardGet";
    public static final String FORWARD_PUT_RESPONSE = "peers.ForwardPut";

    //To wieracentral
    public static final String LOCK_GLOBAL_READ_RESPONSE = "central.LockGlobalRead";
    public static final String LOCK_GLOBAL_WRITE_RESPONSE = "central.LockGlobalWrite";
    public static final String UNLOCK_GLOBAL_RESPONSE = "central.UnLockGlobal";
    public static final String CHANGE_DATA_PLACEMENT = "central.ChangeDataPlacement";
    public static final String CHANGE_EVENT_RESPONSE = "central.ChangeEventResponse";
    public static final String CHANGE_PRIMARY_RESPONSE = "central.ChangePrimaryResponse";


    public static final String INCREASE_VERSION = "IncreaseVersion";

    //Consistency response
    //Data distribution
    public static final String EVENTUAL_CONSISTENCY = "peers.consistency.EventualConsistency";
    public static final String PRIMARY_BACKUP_CONSISTENCY = "peers.consistency.PrimaryBackupConsistency";
    public static final String MULTIPLE_PRIMARIES_CONSISTENCY = "peers.consistency.MultiplePrimariesConsistency";
    public static final String QUORUM_CONSISTENCY = "peers.consistency.QuorumConsistency";
    public static final String TRIPS = "TripS";

    //Available Params
    public static final String TO = "to";
    public static final String SYNC = "sync";
    public static final String UPDATE_TO = "update_to";
    public static final String FROM = "from";
    public static final String ALL = "all";
    public static final String LATENCY_THRESHOLD = "latency_threshold";
    public static final String CAPACITY_THRESHOLD = "capacity_threshold";
    public static final String PERIOD_THRESHOLD = "period_threshold";

    public static final String LT = "<";
    public static final String GT = ">";
    public static final String EQUAL = "==";
    public static final String LTOE = "<=";
    public static final String GTOE = ">=";

    //public static final String UPPER_THRESHOLD = "upper_threshold";
    public static final String PERCENT = "percent";
    public static final String DIRTY = "dirty";
    public static final String PIN = "pin";
    public static final String TIMER_PERIOD = "period";
    public static final String RATE = "rate";
    public static final String TARGET_LOCALE = "target_locale";
    public static final String TARGET_LOCALE_LIST = "target_locale_list";
    public static final String DYNAMIC_LOCALES = "dynamic_locales";
    public static final String LOCALE_LIST = "locale_list";
    public static final String ALL_PEER_HOSTNAMES = "all_peer_hostname";
    public static final String CONFLICT_CHECK = "conflict_check";
    public static final String LAZY_UPDATE = "lazy_update";
    public static final String REASON = "reason";
    public static final String WORKER_CNT = "worker_cnt";
    public static final String GLOBAL_LOCK = "global_lock";
    public static final String DEFAULT = "default";
    public static final String OPERATION = "operation";
    public static final String RELATIONAL_OPERATION = "relational_operator";
    public static final String OLDEST= "oldest";
    public static final String NEWEST = "newest";

    //Error message
    public static final String NOT_HANDLED = "Not handled";
    public static final String NOT_SUPPORTED = "Not supported yet";
    public static final String NOT_SUPPORTED_QUERY = "Not supported query";
    public static final String NO_VERSION = "Failed to find key with given version";
    public static final String NO_META = "Failed to find Meta data with given key";
    public static final String PUT_IN_PROGRESS_FOR_KEY = "Write for the key is in pregress";
}
