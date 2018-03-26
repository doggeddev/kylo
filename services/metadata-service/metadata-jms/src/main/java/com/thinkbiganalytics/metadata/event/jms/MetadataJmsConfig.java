package com.thinkbiganalytics.metadata.event.jms;

/*-
 * #%L
 * thinkbig-metadata-jms
 * %%
 * Copyright (C) 2017 ThinkBig Analytics
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.thinkbiganalytics.jms.JmsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Initialize JMS objects.
 */
@Configuration
public class MetadataJmsConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection") //wired by plugins
    @Inject
    private JmsService jmsService;

    /**
     * Gets the queue for triggering feeds for cleanup.
     *
     * @return the cleanup trigger queue
     */
    @Bean(name = "cleanupTriggerQueue")
    @Nonnull
    public Queue cleanupTriggerQueue() {
        return jmsService.getQueue(MetadataQueues.CLEANUP_TRIGGER);
    }

    /**
     * Gets the queue for triggering feeds based on preconditions.
     *
     * @return the precondition trigger queue
     */
    @Bean(name = "preconditionTriggerQueue")
    @Nonnull
    public Queue preconditionTriggerQueue() {
        return jmsService.getQueue(MetadataQueues.PRECONDITION_TRIGGER);
    }
    
    /**
     * Gets the topic for triggering the canceling of active water marks.
     *
     * @return the water mark cancel even topic
     */
    @Bean(name = MetadataTopics.CANCEL_ACTIVE_WATER_MARK)
    @Nonnull
    public Topic cancelActiveWaterMarkTopic() {
        return jmsService.getTopic(MetadataTopics.CANCEL_ACTIVE_WATER_MARK);
    }
    
    /**
     * Gets the topic for triggering the canceling of active water marks.
     *
     * @return the water mark cancel even topic
     */
    @Bean(name = MetadataTopics.FEED_INIT_STATUS_CHANGE)
    @Nonnull
    public Topic feedInitStatusChangeTopic() {
        return jmsService.getTopic(MetadataTopics.FEED_INIT_STATUS_CHANGE);
    }


}
