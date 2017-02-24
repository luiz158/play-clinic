package com.github.mszarlinski.playframework.petclinic.owners.domain

/**
 * @author mszarlinski on 2017-02-23.
 */
trait OwnerRepository {
  def findAll(): List[Owner]

  def findById(id: Long): Option[Owner]

  def save(owner: Owner): Option[Owner]
}
