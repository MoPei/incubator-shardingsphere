/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.driver.spring.namespace.parser.rule.sharding;

import com.google.common.base.Strings;
import org.apache.shardingsphere.driver.spring.namespace.constants.rules.sharding.SPIBeanDefinitionParserTag;
import org.apache.shardingsphere.driver.spring.namespace.factorybean.ShardingAlgorithmFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.util.Properties;

/**
 * Sharding algorithm bean parser for spring namespace.
 */
public final class ShardingAlgorithmBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(final Element element, final ParserContext parserContext) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(ShardingAlgorithmFactoryBean.class);
        factory.addConstructorArgValue(element.getAttribute(SPIBeanDefinitionParserTag.SHARDING_ALGORITHM_TYPE_ATTRIBUTE));
        parseProperties(element, factory);
        return factory.getBeanDefinition();
    }

    private void parseProperties(final Element element, final BeanDefinitionBuilder factory) {
        String properties = element.getAttribute(SPIBeanDefinitionParserTag.SHARDING_ALGORITHM_PROPERTY_REF_ATTRIBUTE);
        if (!Strings.isNullOrEmpty(properties)) {
            factory.addConstructorArgReference(properties);
        } else {
            factory.addConstructorArgValue(new Properties());
        }
    }
}