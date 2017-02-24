package com.github.mszarlinski.playframework.petclinic.owners.port.adapters.anorm

import javax.inject.{Singleton, Inject}

import anorm.SqlParser._
import anorm._
import com.github.mszarlinski.playframework.petclinic.owners.domain.{Owner, OwnerRepository}
import play.api.db.Database

/**
 * @author mszarlinski on 2017-02-23.
 */
@Singleton
class OwnerSqlRepository @Inject()(db: Database) extends OwnerRepository {

  val ownerParser: RowParser[Owner] = {
    long("owners.id") ~
      str("owners.first_name") ~
      str("owners.last_name") map {
      case id ~ firstName ~ lastName =>
        Owner(id, firstName, lastName)
    }
  }

  override def findAll(): List[Owner] = {
    db.withConnection { implicit connection =>
      SQL(
        """
        SELECT o.id, o.first_name, o.last_name, o.address, o.city, o.telephone
        FROM owners o
        ORDER BY o.last_name, o.first_name
        """)
        .as(ownerParser *)
    }
  }

  override def findById(id: Long): Option[Owner] = ???

  override def save(owner: Owner): Option[Owner] = {
    db.withConnection { implicit connection =>
      SQL(
        """
        INSERT INTO owners(first_name, last_name)
        VALUES ({firstName}, {lastName})
        """).on('firstName -> owner.firstName, 'lastName -> owner.lastName)
        .executeInsert()
        .map(genId => owner.copy(id = genId))
    }
  }
}
