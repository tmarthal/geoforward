package geoforward

class UrlMappings {

    static mappings = {
		// catch-all controller url
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		

        "/about"(view: "/about")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

		"/${localPath}+"(controller:'forwarding', action: 'summary')
		"/$localPath"(controller:'forwarding', action: 'forward')
    }
}
