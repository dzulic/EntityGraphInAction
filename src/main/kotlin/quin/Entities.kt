package quin

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedAttributeNode
import javax.persistence.NamedEntityGraph
import javax.persistence.NamedSubgraph
import javax.persistence.OneToMany
import javax.persistence.Table


@NamedEntityGraph(
    name = "organization-with-locations",
    attributeNodes = [
        NamedAttributeNode(value = "location", subgraph = "locations-subgraph")
    ],
    subgraphs = [
        NamedSubgraph(
            name = "locations-subgraph",
            attributeNodes = [NamedAttributeNode("address")]
        )
    ]
)
@Entity
@Table(name = "organization")
data class Organization(
    @Id
    val id: UUID,
    val name: String,
    @OneToMany(mappedBy = "organization")
    val location: List<Location>
)

@NamedEntityGraph(
    name = "locations-with-org-and-address",
    attributeNodes = [
        NamedAttributeNode("organization"),
        NamedAttributeNode("address")
    ]
)
@Entity
@Table(name = "location")
data class Location(
    @Id
    val id: UUID,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "organization")
    val organization: Organization,
    @ManyToOne
    @JoinColumn(name = "address")
    val address: Address
) {
    override fun toString(): String {
        return "$name $address Organization: ${organization.id}"
    }
}

@Entity
@Table(name = "address")
data class Address(
    @Id
    val id: UUID,
    val name: String
)


interface OrganizationRepository : JpaRepository<Organization, UUID> {
    // referencing a named entity graph
    @EntityGraph(value = "organization-with-locations", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select o from Organization o")
    fun findAllByEntity(): List<Organization>

    // ad-hoc entity graph
    @EntityGraph(attributePaths = ["location"])
    @Query("select o from Organization o")
    fun findAllWithLocations(): List<Organization>
}

interface LocationRepository : JpaRepository<Location, UUID> {

    // referencing a named entity graph
    @EntityGraph(value = "locations-with-org-and-address", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Location l")
    fun findAllByEntity(): List<Location>

    // ad-hoc entity graph
    @EntityGraph(attributePaths = ["address"])
    @Query("select l from Location l")
    fun findAllWithEntity(): List<Location>


}
