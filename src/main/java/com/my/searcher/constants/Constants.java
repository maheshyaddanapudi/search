package com.my.searcher.constants;
public final class Constants {

    private Constants() {
        // do nothing
    }

    public static final Integer INTERVAL_IN_MILLISECOND = 86400_000;
    public static final String MODIFICATION_DATE = "updateTimestamp";

    public static final String SEARCH_QUERY = "/query";
    public static final String QUERY = "query";
    public static final String HITS = "hits";
    public static final String TOOK = "took";
    public static final String TOTAL_HITS = "total";
    public static final String VALUE = "value";
    public static final String _SOURCE = "_source";
    public static final String CONTENT_ACCEPT = "Accept";
    public static final String APP_TYPE = "application/json";
    public static final String CONTENT_TYPE = "Content-type";
    public static final Integer TO_MS = 1000;
    public static final String ENCODING_UTF8 = "UTF-8";
    
    public static final String CACHE_NAME = "searcher";
    public static final String HAZLECAST_INSTANCE = "HAZLECAST_INSTANCE";

    public static final String SAVE = "/save";
    public static final String FIND_ONE = "/find-one";
    public static final String RECORD_ID = "recordId";
    public static final String FIND_ALL = "/find-all";
    public static final String SEARCH_CONTROLLER = "/search";
    public static final String SYNC_CONTROLLER = "/index";
    public static final String SYNC_TRIGGER = "/sync";
    public static final String RECORD_CONTROLLER = "/record";
    public static final String RECORD_ADD = "/add";
    public static final String RECORD_UPDATE = "/update";
    public static final String RECORD_DELETE = "/delete";
    
    public static final String CACHE_CONTROLLER = "/app-cache";
    public static final String CACHE_CLEAR = "/clear";
    
    // Database Common Constants
    public static final String FALSE = "false";
    public static final String MYSQL = "mysql";
    public static final String TRUE= "true";
    public static final String DOT = ".";
    public static final String dataSource = "dataSource";
    public static final String DATASOURCE = "datasource";
    public static final String CACHE_PREP_STMTS = "cachePrepStmts";
    public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String _256 = "256";
    public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    public static final String _2048 = "2048";
    public static final String USER_SERVER_PREP_STMTS = "useServerPrepStmts";
    public static final String UTC = "UTC";
    public static final String _100 = "100";
    public static final String _2 = "2";
    public static final String CONNECTION_INIT_SQL = "connectionInitSql";
    public static final String CONNECTION_INIT_SQL_VALUE = "set character_set_client = utf8mb4;";
    public static final String PRE_CONDUCTOR = "pre-conductor";
    public static final String utf8mb4_unicode_ci = "utf8mb4_unicode_ci";
    public static final String USE_LEGACY_DATETIME_CODE = "useLegacyDatetimeCode";
    public static final String SERVER_TIMEZONE = "serverTimezone";
    public static final String CONNECTION_COLLATION = "connectionCollation";
    public static final String USE_SSL = " useSSL";
    public static final String AUTO_RECONNECT = "autoReconnect";
    public static final String POOL_NAME = "poolName";
    public static final String MAX_POOL_SIZE = "maximumPoolSize";
    public static final String MIN_IDLE = "minimumIdle";
    public static final String MAX_LIFETIME = "maxLifetime";
    public static final String DATASOURCE_PROPERTIES = "dataSourceProperties";
    public static final String DRIVER_CLASS_NAME = "driverClassName";
    public static final String JDBC_URL = "jdbcUrl";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
}