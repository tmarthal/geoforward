package geoforward

import geoforward.ForwardSetup

public class ForwardingController {

	def forward(String localPath) {
		println localPath
		
		// Finder magic
		ForwardSetup fs = ForwardSetup.findByLocalPath(localPath)
		
		if (!fs) {
			log.error("forwarding to unrecognized path ${localPath}")
			return response.sendError(404)
		}
		println "forwarding to ${fs.forwardUrl}"
		redirect(url: fs.forwardUrl)
	}
	
	
	def summary(String localPath) {
		ForwardSetup fs = ForwardSetup.findByLocalPath(localPath)
		
		respond "summary", model:[forwardSetup:fs, totalClicks: 1000]
	}
}
