package enlist.grails

import org.apache.commons.lang.StringUtils

class RegisterController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def mailService

	def create() {
		[user:new User()]
	}

	def submit() {
		log.debug "Saving new user for registration params:$params"
		def user = new User(params)
		user.status = Status.findByStatus("Pending")
        if (StringUtils.isBlank(user.address?.address1))  user.address = null
		user.save(failOnError:true)
		log.debug "Sending email for registration confirmation for ${user.username}"
		mailService.sendMail {
			to user.email
			subject "Registration Confirmation"
			html "${user.firstName} ${user.lastName},<br><br>Thanks for registering with Enlist.  Click <a href=${g.createLink(controller:'register',action:'confirm',absolute:true,id:user.id)}>here</a> to confirm your registration."
		}
		flash.message = "An email confirmation has been sent. Follow the instructions there."
		redirect(controller: "dashboard", action: "index")
	}

	def confirm() {
		def user = User.get(params.id)
		user.status = Status.findByStatus("Active")

		flash.message = "Thanks ${user.firstName}, your registration is complete."
		redirect(controller: "event", action: "list")
	}
}
