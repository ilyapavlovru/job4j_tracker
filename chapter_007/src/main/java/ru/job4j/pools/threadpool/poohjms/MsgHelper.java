package ru.job4j.pools.threadpool.poohjms;

import org.codehaus.jackson.JsonNode;

class MsgHelper {
    static boolean isQueueMessage(JsonNode jsonNode) {
        return jsonNode.has("queue") ;
    }

    static boolean isTopicMessage(JsonNode jsonNode) {
        return jsonNode.has("topic") ;
    }
}
