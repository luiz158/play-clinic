package com.github.mszarlinski.playframework.petclinic.owners.port.adapters.rest

import javax.inject.{Inject, Singleton}

import com.github.mszarlinski.playframework.petclinic.owners.domain.{Owner, OwnerRepository}
import play.api.libs.json.JsSuccess
import play.api.libs.json.Json._
import play.api.mvc.{Action, BodyParsers, Controller}

/**
 * @author mszarlinski on 2017-02-23.
 *
 * @see http://mandubian.com/2012/09/08/unveiling-play-2-dot-1-json-api-part1-jspath-reads-combinators/
 */
@Singleton
class OwnersEndpoint @Inject()(ownerRepository: OwnerRepository)(implicit ownerFormatter: OwnerFormatter) extends Controller {

  def getOwners = Action {
    val owners = ownerRepository.findAll()
    Ok(toJson(owners))
  }

  def saveOwner = Action(BodyParsers.parse.json) { req =>
    req.body.validate[Owner] match {// TODO: fold
      case owner: JsSuccess[Owner] =>
        val saved = ownerRepository.save(owner.get)
        Ok(toJson(saved))
      case _ => BadRequest
    }
  }
}
