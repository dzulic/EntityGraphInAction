package quin

import org.springframework.stereotype.Service

@Service
class MainService(
    private val organizationRepository: OrganizationRepository,
    private val locationRepository: LocationRepository
) {

    fun getOrganizations() {
        println("_______NEW___________")
        println("______________________")


        println("no entity graph_____________")
        val organizations1 = organizationRepository.findAll()
        println("BEFORE LOADING:")
        println()
        println("${organizations1.first()}")

        println()
        println("named entity graph_____________")
        val organizations2 = organizationRepository.findAllByEntity()
        println("BEFORE LOADING:")
        println()
        println("${organizations2.first()}")

        println()
        println("attribute entity graph_____________")
        val organization3 = organizationRepository.findAllWithLocations()
        println("BEFORE LOADING:")
        println()
        println("${organization3.first()}")
        println("_____________")

    }

    fun getLocations() {
        println("_______NEW___________")
        println("______________________")

        println("no entity")
        val location1 = locationRepository.findAll()
        println("${location1.first()}")


        println("named entity_____________")
        val location2 = locationRepository.findAllByEntity()
        println("${location2.first()}")


        println("attribute entity graph_____________")
        val location3 = locationRepository.findAllWithEntity()
        println("${location3.first()}")


        println("_____________")
    }

}