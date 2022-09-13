// 创建watcher前必须配置模版，不然匹配不到结果
PUT _template/error-log-collector-
{
    "template": "error-log-collector*",
    "order": 0,
    "settings": {
        "index": {
            "refresh_interval": "5s"
        }
    },
    "mappings": {
        "_default_": {
            "dynamic_templates": [
                {
                    "message_field": {
                        "match_mapping_type": "string",
                        "path_match": "message",
                        "mapping": {
                            "norms": false,
                            "type": "text",
                            "analyzer": "ik_max_word",
                            "search_analyzer": "ik_max_word"
                        }
                    }
                },
                {
                    "throwable_field": {
                        "match_mapping_type": "string",
                        "path_match": "throwable",
                        "mapping": {
                            "norms": false,
                            "type": "text",
                            "analyzer": "ik_max_word",
                            "search_analyzer": "ik_max_word"
                        }
                    }
                },
                {
                    "string_fields": {
                        "match_mapping_type": "string",
                        "match": "*",
                        "mapping": {
                            "norms": false,
                            "type": "text",
                            "analyzer": "ik_max_word",
                            "search_analyzer": "ik_max_word",
                            "fields": {
                                "keyword": {
                                    "type": "keyword"
                                }
                            }
                        }
                    }
                }
            ],
            "_all": {
                "enabled": false
            },
            "properties": {
                "hostName": {
                    "type": "keyword"
                },
                "ip": {
                    "type": "ip"
                },
                "level": {
                    "type": "keyword"
                },
                "currentDateTime": {
                    "type": "date"
                }
            }
        }
    }
}