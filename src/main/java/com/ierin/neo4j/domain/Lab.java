package com.ierin.neo4j.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 *
 */
@NodeEntity
public class Lab {

    @GraphId
    private Long nodeId;

    @Indexed(indexName = "SINGLE_STRING_INDEX")
    private String single;

    @Indexed(indexName = "MULTIPLE_STRING_INDEX")
    private String foo;

    @Indexed(indexName = "MULTIPLE_STRING_INDEX")
    private String bar;

    @Indexed(indexName = "SINGLE_LONG_INDEX")
    private Long singleLong;

    @Indexed(indexName = "MULTIPLE_LONG_INDEX")
    private Long fooLong;

    @Indexed(indexName = "MULTIPLE_LONG_INDEX")
    private Long barLong;

    Lab() {
    }

    public Lab(String single, String foo, String bar, Long singleLong, Long fooLong, Long barLong) {
        this.single = single;
        this.foo = foo;
        this.bar = bar;
        this.singleLong = singleLong;
        this.fooLong = fooLong;
        this.barLong = barLong;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public String getSingle() {
        return single;
    }

    public String getFoo() {
        return foo;
    }

    public String getBar() {
        return bar;
    }

    public Long getSingleLong() {
        return singleLong;
    }

    public Long getFooLong() {
        return fooLong;
    }

    public Long getBarLong() {
        return barLong;
    }
}
