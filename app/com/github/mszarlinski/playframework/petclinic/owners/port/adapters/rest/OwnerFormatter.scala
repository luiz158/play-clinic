package com.github.mszarlinski.playframework.petclinic.owners.port.adapters.rest

import javax.inject.Singleton

import com.github.mszarlinski.playframework.petclinic.owners.domain.Owner
import play.api.libs.json._

/**
 * @author mszarlinski on 2017-02-24.
 */
@Singleton
class OwnerFormatter extends Writes[Owner] with Reads[Owner] {
  override def writes(owner: Owner) = Json.obj(
    "id" -> owner.id,
    "first_name" -> owner.firstName,
    "last_name" -> owner.lastName
  )

  override def reads(json: JsValue): JsResult[Owner] = {
    for {
      id <- (json \ "id").validate[Long]
      firstName <- (json \ "first_name").validate[String]
      lastName <- (json \ "last_name").validate[String]
    } yield Owner(id, firstName, lastName)
  }
}
