package geoforward

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ForwardSetupController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ForwardSetup.list(params), model:[forwardSetupCount: ForwardSetup.count()]
    }

    def show(ForwardSetup forwardSetup) {
        respond forwardSetup
    }

    def create() {
        respond new ForwardSetup(params)
    }

    @Transactional
    def save(ForwardSetup forwardSetup) {
        if (forwardSetup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (forwardSetup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond forwardSetup.errors, view:'create'
            return
        }

        forwardSetup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'forwardSetup.label', default: 'ForwardSetup'), forwardSetup.id])
                redirect forwardSetup
            }
            '*' { respond forwardSetup, [status: CREATED] }
        }
    }

    def edit(ForwardSetup forwardSetup) {
        respond forwardSetup
    }

    @Transactional
    def update(ForwardSetup forwardSetup) {
        if (forwardSetup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (forwardSetup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond forwardSetup.errors, view:'edit'
            return
        }

        forwardSetup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'forwardSetup.label', default: 'ForwardSetup'), forwardSetup.id])
                redirect forwardSetup
            }
            '*'{ respond forwardSetup, [status: OK] }
        }
    }

    @Transactional
    def delete(ForwardSetup forwardSetup) {

        if (forwardSetup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        forwardSetup.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'forwardSetup.label', default: 'ForwardSetup'), forwardSetup.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'forwardSetup.label', default: 'ForwardSetup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
