/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.amqp.tutorials.tut1;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gary Russell, Scott Deeg
 *
 */
public class Tut1Sender implements Runnable {

	private static Logger logger = Logger.getLogger(Tut1Sender.class);

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	@Override
	public void run() {
		while (true) {
			String message = "Hello World!";
			template.convertAndSend(queue.getName(), message);
			logger.error(" [x] Sent '" + message + "'");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}
}
