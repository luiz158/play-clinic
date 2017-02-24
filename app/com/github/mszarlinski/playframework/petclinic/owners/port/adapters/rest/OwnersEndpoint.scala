package com.github.mszarlinski.playframework.petclinic.owners.port.adapters.rest

import javax.inject.{Inject, Singleton}

import com.github.mszarlinski.playframework.petclinic.owners.domain.{Owner, OwnerRepository}
import play.api.libs.json.Json._
import play.api.libs.json.{Json, Writes}
import play.api.mvc.{Action, Controller}

/**
 * @author mszarlinski on 2017-02-23.
 *
 * @see http://mandubian.com/2012/09/08/unveiling-play-2-dot-1-json-api-part1-jspath-reads-combinators/
 */
@Singleton
class OwnersEndpoint @Inject()(ownerRepository: OwnerRepository) extends Controller {
  // TODO: use combinators
  implicit val ownerWrites = new Writes[Owner] {
    override def writes(owner: Owner) = Json.obj(
      "id" -> owner.id,
      "first_name" -> owner.firstName,
      "last_name" -> owner.lastName
    )
  }

  def getOwners = Action {
    val owners = ownerRepository.findAll()
    Ok(toJson(owners))
  }
}
