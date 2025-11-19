package ru.tbank.education.school.lesson2.travelCompany

class Agency {
    private val tours = mutableListOf<TourProduct>()

    fun addTour(tour: TourProduct) {
        tours.add(tour)
    }

    fun showTours() {
        println("Available tours:")
        for (tour in tours) {
            println(tour.info())
        }
    }
}
