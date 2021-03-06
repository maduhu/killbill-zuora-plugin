/*
 * Copyright 2010-2013 Ning, Inc.
 *
 *  Ning licenses this file to you under the Apache License, version 2.0
 *  (the "License"); you may not use this file except in compliance with the
 *  License.  You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package com.ning.killbill.zuora.zuora;

import org.apache.commons.pool.impl.GenericObjectPool;

import com.ning.killbill.zuora.zuora.setup.ZuoraConfig;

public class ConnectionPool extends GenericObjectPool {

    private static Config createPoolConfig(ZuoraConfig zuoraConfig) {
        Config config = new Config();

        config.minIdle = zuoraConfig.getPoolMinIdle();
        config.maxIdle = zuoraConfig.getPoolMaxIdle();
        config.maxActive = zuoraConfig.getPoolMaxActive();
        return config;
    }

    public ConnectionPool(ConnectionFactory factory, ZuoraConfig zuoraConfig) {
        super(factory, createPoolConfig(zuoraConfig));
    }

    public ZuoraConnection borrowFromPool() throws PoolException {
        try {
            return (ZuoraConnection) borrowObject();
        } catch (Exception ex) {
            throw new PoolException(ex);
        }
    }

    public void returnToPool(ZuoraConnection connection) throws PoolException {
        try {
            returnObject(connection);
        } catch (Exception ex) {
            throw new PoolException(ex);
        }
    }


    @Override
    public int getMinIdle() {
        return super.getMinIdle();
    }

    @Override
    public void setMinIdle(int minIdle) {
        super.setMinIdle(minIdle);
    }

    @Override
    public int getMaxIdle() {
        return super.getMaxIdle();
    }

    @Override
    public void setMaxIdle(int maxIdle) {
        super.setMaxIdle(maxIdle);
    }

    @Override
    public int getMaxActive() {
        return super.getMaxActive();
    }

    @Override
    public void setMaxActive(int maxActive) {
        super.setMaxActive(maxActive);
    }

    @Override
    public int getNumActive() {
        return super.getNumActive();
    }

    @Override
    public int getNumIdle() {
        return super.getNumIdle();
    }
}
