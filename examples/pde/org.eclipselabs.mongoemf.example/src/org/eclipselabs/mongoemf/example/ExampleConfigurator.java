/*******************************************************************************
 * Copyright (c) 2013 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *******************************************************************************/

package org.eclipselabs.mongoemf.example;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipselabs.emongo.MongoClientProvider;
import org.eclipselabs.emongo.MongoDatabaseProvider;
import org.eclipselabs.emongo.config.ConfigurationProperties;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author bhunt
 * 
 */
public class ExampleConfigurator
{
	private volatile ConfigurationAdmin configurationAdmin;

	public void activate() throws IOException
	{
		Configuration config = configurationAdmin.createFactoryConfiguration(ConfigurationProperties.CLIENT_PID, null);

		Dictionary<String, Object> properties = new Hashtable<String, Object>();

		properties.put(MongoClientProvider.PROP_CLIENT_ID, "example");
		properties.put(MongoClientProvider.PROP_URI, "mongodb://localhost");
		config.update(properties);

		config = configurationAdmin.createFactoryConfiguration(ConfigurationProperties.DATABASE_PID, null);
		properties = new Hashtable<String, Object>();
		properties.put(MongoDatabaseProvider.PROP_CLIENT_FILTER, "(" + MongoClientProvider.PROP_CLIENT_ID + "=example)");
		properties.put(MongoDatabaseProvider.PROP_ALIAS, "example");
		properties.put(MongoDatabaseProvider.PROP_DATABASE, "test");
		config.update(properties);
	}

	public void bindConfigurationAdmin(ConfigurationAdmin configurationAdmin)
	{
		this.configurationAdmin = configurationAdmin;
	}
}
