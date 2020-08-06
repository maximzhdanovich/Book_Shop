package com.bookshop.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.SolrResponseBase;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.ContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@EnableAutoConfiguration
@EnableScheduling
@Service
public class DataImportScheduler {

    private final SolrTemplate solrTemplate;

    @Autowired
    public DataImportScheduler(SolrTemplate solrTemplate) {
        this.solrTemplate = solrTemplate;
    }



    @Scheduled(fixedDelay = 5000)
    public void importData() throws IOException {
        SolrResponse solrResponse = solrTemplate.execute(solrClient -> {
            SolrRequest solrRequest = new SolrRequest(SolrRequest.METHOD.GET, "/dataimport?command=full-import&clean=false&commit=true") {
                @Override
                public SolrParams getParams() {
                    return null;
                }

                @Override
                public Collection<ContentStream> getContentStreams() throws IOException {
                    return null;
                }

                @Override
                protected SolrResponse createResponse(SolrClient solrClient){
                    SolrResponseBase response = new SolrResponseBase();
                    try {
                        response.setResponse(solrClient.request(this));
                    } catch (SolrServerException | IOException e) {
                        e.printStackTrace();
                    }
                    return response;
                }
            };
            solrRequest.setResponseParser(new XMLResponseParser());
            return solrRequest.process(solrClient);
        });

    }
}
