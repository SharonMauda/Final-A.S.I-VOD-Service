package controller;

import model.UpdateDBService;

// A class that servers the admin through his menu
// Allowing the admin to write all changes made to the file and update it
// May happen is the admin chooses to do so, and happens automatically when the admin logs out

public class UpdateDBController {

	private UpdateDBService updateObj;

	public UpdateDBController() {
		updateObj = UpdateDBService.getInstance();
	}

	// Calls the Service to write the updated map to the Movie DB file
	public void syncDB() throws Exception {
		boolean result = true;
			result = updateObj.mapToFile();
			if (!result)
				throw new Exception("Map is empty! Could not write to file.\n");
	}
}
