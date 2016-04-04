package geoforward

class ForwardSetup {

	// 
	String localPath
	
	// External url
	String forwardUrl //TODO make this a List
	
    static constraints = {
		forwardUrl url: true
    }
}
