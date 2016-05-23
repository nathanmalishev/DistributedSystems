package activitystreamer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import activitystreamer.database.DBShard;
import activitystreamer.keyregister.RegisterSolution;
import activitystreamer.util.Settings;

public class KeyRegister {
	
	private static final Logger log = LogManager.getLogger();
	
	private static void help(Options options){
		String header = "An ActivityStream Client for Unimelb COMP90015\n\n";
		String footer = "\ncontact aharwood@unimelb.edu.au for issues.";
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("ActivityStreamer.Client", header, options, footer, true);
		System.exit(-1);
	}
	
	public static void main(String[] args) {
		
		log.info("reading command line options");

		Options options = new Options();
		options.addOption("lp",true,"local port number");
		options.addOption("lh",true,"local hostname");
		options.addOption("s",true,"secret for the server to use");

		// build the parser
		CommandLineParser parser = new DefaultParser();
		
		CommandLine cmd = null;
		try {
			cmd = parser.parse( options, args);
		} catch (ParseException e1) {
			help(options);
		}
		
		if(cmd.hasOption("lp")){
			try{
				int port = Integer.parseInt(cmd.getOptionValue("lp"));
				Settings.setLocalPort(port);
			} catch (NumberFormatException e){
				log.info("-lp requires a port number, parsed: "+cmd.getOptionValue("lp"));
				help(options);
			}
		}
		
		try {
			Settings.setLocalHostname(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			log.warn("failed to get localhost IP address");
		}
		
		if(cmd.hasOption("lh")){
			Settings.setLocalHostname(cmd.getOptionValue("lh"));
		}


		// Assign Random secret if we haven't been given one
		if(cmd.hasOption("s")){
			Settings.setSecret(cmd.getOptionValue("s"));
		}
		else{
			Settings.setSecret(Settings.nextSecret());
		}

		log.info("starting register with secret: " + Settings.getSecret());
		
		
		final RegisterSolution kr = new RegisterSolution(Settings.getLocalPort());
		
		// the following shutdown hook doesn't really work, it doesn't give us enough time to
		// cleanup all of our connections before the jvm is terminated.
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {  
				kr.setTerm(true);
				kr.interrupt();
		    }
		 });
	}
}
