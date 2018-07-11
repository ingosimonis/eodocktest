
public class EOMain {

	public static void main(String[] args) {
		
		// defaults
		String in = "/inData";
		String aoi = "myAOI";
		String out = "/outData";
		
		
		if(args.length == 0) {
			System.out.println("EOMain runs with defaults!");
			EORunner eor = new EORunner(in, aoi, out);
			eor.run();
			System.exit(0);
		}
		if(args.length != 3) {
			System.out.println("Requires three arguments: inputDir, AoI, outputDir");
			System.exit(0);
		}
		
		EORunner eor = new EORunner(args[0], args[1], args[2]);
		eor.run();
	}

}
