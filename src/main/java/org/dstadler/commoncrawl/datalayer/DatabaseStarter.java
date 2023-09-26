package org.dstadler.commoncrawl.datalayer;

import org.apache.derby.drda.NetworkServerControl;

import java.io.PrintWriter;

public final class DatabaseStarter {
	private static final int DEFAULT_PORT = 11527;

	public static final String DERBY_PORT_NUMBER = "derby.drda.portNumber";
	public static final String DERBY_HOST = "derby.drda.host";
    public static final String DERBY_SYSTEM_HOME = "derby.system.home";

	private DbmsProcedure proc;
	private boolean started = false;

	public void start() {
		start(DEFAULT_PORT);
	}

	public void start(int port) {
		proc = new DbmsProcedure(port);

		// don't start it twice if it is already running
		if(!proc.isRunning()) {
			System.out.println("Starting database procedure");

			proc.setConsoleWriter(new PrintWriter(System.out));

			started = true;
			proc.run();
		}

	}

	public boolean stop() {
		if(started) {
			System.out.println("Stopping database procedure");

			started = false;
			return proc.stop();
		}

		return true;
	}

	/**
	 * Start and stop the Derby Database Management System
	 */
	@SuppressWarnings("CallToPrintStackTrace")
	private static class DbmsProcedure {
	    private NetworkServerControl dbServer = null; // NOPMD
	    private PrintWriter consoleWriter = null;
        private final int dbServerPort;

	    public DbmsProcedure(int port) {
	    	this.dbServerPort = port;
	        System.setProperty(DERBY_PORT_NUMBER, Integer.toString(dbServerPort));
	        System.setProperty(DERBY_HOST, "localhost");
	        //System.setProperty(DERBY_LOGGER_METHOD, DerbyLogger.getLogMethod());
	        System.setProperty(DERBY_SYSTEM_HOME, /*Directories.getExistingDatabaseDir().getAbsolutePath()*/ ".");

	        /* This does not work on a Linux VirtualBox, somehow we do always get the port as taken!
	        if(SocketUtils.isPortAvailable(dbServerPort, null)) {
	        	log.warning(TextUtils.merge("Unable to create database server controller for ''{0}:{1,number,#}'' (<address>:<port>), port is already taken.", dbServerAddress, dbServerPort));
	        	return;
	        }*/

	        try {
	            dbServer = new NetworkServerControl();
	        } catch (Exception e) {
	            System.out.println("Unable to create database server controller for ''localhost:" + dbServerPort + "'' (<address>:<port>).");
				e.printStackTrace();
	        }
		}

		/**
	     * @param consoleWriter The {@link PrintWriter} to which server console will be output. Console output will be disabled if <code>null</code> is passed in.
	     */
	    public void setConsoleWriter(PrintWriter consoleWriter) {
	        this.consoleWriter = consoleWriter;
	    }

	    public void run() {
	        System.out.println("Starting internal database management system on host: '" + "localhost" + "' and port: '" + dbServerPort + "'");

	        try {
	            dbServer.start(consoleWriter);

	            for(int i = 0;i < 10;i++) {
	            	if(isRunning()) {
	            		break;
	            	}
	            	Thread.sleep(1000);
	            }
			} catch (Exception e) {
	            System.out.println("Unable to start embedded database (Derby) on host: '" + "localhost" +
	                    "' and port: '" + dbServerPort + "' timed out.");
				e.printStackTrace();
			}
	    }

	    public boolean stop() {
	        if (dbServer == null) {
	            System.out.println("Database management system actually not running, so it cannot be stopped.");
	            return true;
	        }

	        try {
	            dbServer.shutdown();
	        } catch (Exception e) {
				e.printStackTrace();
	            return false;
	        }

	        return true;
	    }

	    /**
	     * Check if the database is running.
	     * @return <code>true</code> if the database is running or <code>false</code> otherwise
	     */
	    public boolean isRunning() {
	        if (dbServer == null) {
	            return false;
	        }

	        try {
	            dbServer.ping();
	            return true;
	        } catch (@SuppressWarnings("unused") Exception e) {
	            return false;
	        }
	    }
	}
}
