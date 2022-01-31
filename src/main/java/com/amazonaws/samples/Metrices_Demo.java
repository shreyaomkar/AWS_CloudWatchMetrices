package com.amazonaws.samples;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.ListMetricsRequest;
import software.amazon.awssdk.services.cloudwatch.model.ListMetricsResponse;
import software.amazon.awssdk.services.cloudwatch.model.Metric;

public class Metrices_Demo 
{
	public static void main(String[] args)
	{
        String namespace = "AWS/DynamoDB";
        CloudWatchClient cw = CloudWatchClient.builder().build();

        boolean done = false;
        String next_token = null;

        while(!done) 
        {
            
        	ListMetricsResponse response;

        	if (next_token == null) 
        	{
        		ListMetricsRequest request = ListMetricsRequest.builder()
        				.namespace(namespace)
        				.build();

        		response = cw.listMetrics(request);
        	}
        	else
        	{
        		ListMetricsRequest request = ListMetricsRequest.builder()
                        .namespace(namespace)
                        .nextToken(next_token)
                        .build();

        		response = cw.listMetrics(request);
        	}

            for(Metric metric : response.metrics()) 
            {
                System.out.printf(
                    "Retrieved metric %s", metric.metricName());
                System.out.println();
            }

            if(response.nextToken() == null) 
            {
                done = true;
            }
            else 
            {
            	next_token = response.nextToken();
            }
        }
	}
}
