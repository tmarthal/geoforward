package geoforward

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/about"(view: "/about")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
