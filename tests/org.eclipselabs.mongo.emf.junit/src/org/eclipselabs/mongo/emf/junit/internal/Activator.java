/*******************************************************************************
 * Copyright (c) 2010 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *******************************************************************************/

package org.eclipselabs.mongo.emf.junit.internal;

import org.eclipselabs.mongo.IMongoDB;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator
{
	public static Activator getInstance()
	{
		return instance;
	}

	public BundleContext getContext()
	{
		return context;
	}

	public IMongoDB getMongoDB()
	{
		return mongoTracker.getService();
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		this.context = context;
		mongoTracker = new ServiceTracker<IMongoDB, IMongoDB>(context, IMongoDB.class, null);
		mongoTracker.open();
		instance = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		instance = null;

		if (mongoTracker != null)
			mongoTracker.close();
	}

	private static Activator instance;

	private BundleContext context;
	private ServiceTracker<IMongoDB, IMongoDB> mongoTracker;
}