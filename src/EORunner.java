import java.io.File;
import java.io.FileWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class EORunner {
	
	private Path inputDir;
	private String aoi;
	private String outputDir;
	private ArrayList<String> stageInFiles;
	
	public EORunner(String inputDir, String AoI, String outputDir) {
		String workingDir = System.getProperty("user.dir");
		this.inputDir = Paths.get(workingDir + inputDir);
		this.aoi = AoI;
		this.outputDir = workingDir + outputDir;
		this.stageInFiles = new ArrayList<String>();
	}
	
	// run method to get all elements executed
	public void run() {
		System.out.println("*****");
		System.out.println("The EORunner app has been started. Configuration:");
		System.out.println("\t inputDir = " + this.inputDir);
		System.out.println("\t AreaOfInterest = " + this.aoi);
		System.out.println("\t outputDir = " + this.outputDir);
		
		System.out.println("Reading stageIn data");		
		int success = this.readStageInData();
		if(success == 1) {
			System.out.println("\tsuccessful");
		} else {
			System.out.println("\tfailed");
		}
		
		System.out.println("Produce result file");	
	    success = this.produceResult();
		if(success == 1) {
			System.out.println("\tsuccessful");
		} else {
			System.out.println("\tfailed");
		}
		
		System.out.println("*****");
	}
	
	// read the stageIn directory
	private int readStageInData() {
		try {
			DirectoryStream<Path> dirPaths = Files.newDirectoryStream(inputDir);
		    for (Path file : dirPaths) {
		    	this.stageInFiles.add(file.getFileName().toString());
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		// if successful, return 1
		return 1;
	}
		

	// writes result file that contains list of all input files
	private int produceResult() {
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
			String formattedDate = formatter.format(date);

			String out = outputDir.concat("/" + formattedDate + "_result.txt");
			File outFile = new File(out);
			outFile.createNewFile();
			FileWriter fw = new FileWriter(outFile);
			
			fw.write(date.toString());
			fw.write(System.getProperty("line.separator"));
			fw.write(System.getProperty("line.separator"));
			
			fw.write("Read the following files from input directory: ");
			fw.write(System.getProperty("line.separator"));
			fw.write("\t"+ inputDir);
			fw.write(System.getProperty("line.separator"));
			
			Iterator<String> iter = stageInFiles.iterator();
			while(iter.hasNext()) {
				fw.write("\t\t" + iter.next());
				fw.write(System.getProperty("line.separator"));
			}
			fw.flush();
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	

}
