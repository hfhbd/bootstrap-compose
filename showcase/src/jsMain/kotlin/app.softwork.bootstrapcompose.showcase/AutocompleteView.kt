package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun AutoCompleteView() {
    autocompleteCities()
    Hr()
    autocompleteCitiesCustom()
}

@Composable
private fun autocompleteCities() {
    var textvalue by remember { mutableStateOf("") }

    Box(attrs = {
        style {
            padding(1.em)
        }
    }) {
        Text("Enter City Name: ")
        Autocomplete(
            value = textvalue,
            onValueChange = {
                textvalue = it
            },
            suggestions = search(textvalue),
            onSelection = {
                textvalue = it
            }
        )
    }
}

@Composable
private fun autocompleteCitiesCustom() {
    var textvalue by remember { mutableStateOf("") }
    Box(attrs = {
        style {
            padding(1.em)
        }
    }) {
        Text("Enter City Name: ")
        Autocomplete(
            value = textvalue,
            onValueChange = {
                textvalue = it
            },
            suggestions = search(textvalue),
            content = { _, item ->
                Text(item)
                Br()
                Span(attrs = {
                    style {
                        fontSize(0.7.cssRem)
                    }
                }) {
                    Text("City description")
                }
            },
            onSelection = { _, item ->
                textvalue = item
            }
        )
    }
}

private const val SEARCH_RESULT_SIZE = 5

private fun search(input: String): List<String> {
    var insertionPoint = allCities.binarySearch {
        it.uppercase().compareTo(input.uppercase())
    }

    if (insertionPoint < 0) {
        insertionPoint = -insertionPoint - 1
    }

    return allCities.subList(
        insertionPoint.coerceAtMost(allCities.lastIndex),
        (insertionPoint + SEARCH_RESULT_SIZE).coerceAtMost(allCities.size)
    )
}

private val allCities = listOf(
    "Alameda",
    "Alhambra",
    "Anaheim",
    "Antioch",
    "Arcadia",
    "Bakersfield",
    "Barstow",
    "Belmont",
    "Berkeley",
    "Beverly Hills",
    "Brea",
    "Buena Park",
    "Burbank",
    "Calexico",
    "Calistoga",
    "Carlsbad",
    "Carmel",
    "Chico",
    "Chula Vista",
    "Claremont",
    "Compton",
    "Concord",
    "Corona",
    "Coronado",
    "Costa Mesa",
    "Culver City",
    "Daly City",
    "Davis",
    "Downey",
    "El Centro",
    "El Cerrito",
    "El Monte",
    "Escondido",
    "Eureka",
    "Fairfield",
    "Fontana",
    "Fremont",
    "Fresno",
    "Fullerton",
    "Garden Grove",
    "Glendale",
    "Hayward",
    "Hollywood",
    "Huntington Beach",
    "Indio",
    "Inglewood",
    "Irvine",
    "La Habra",
    "Laguna Beach",
    "Lancaster",
    "Livermore",
    "Lodi",
    "Lompoc",
    "Long Beach",
    "Los Angeles",
    "Malibu",
    "Martinez",
    "Marysville",
    "Menlo Park",
    "Merced",
    "Modesto",
    "Monterey",
    "Mountain View",
    "Napa",
    "Needles",
    "Newport Beach",
    "Norwalk",
    "Novato",
    "Oakland",
    "Oceanside",
    "Ojai",
    "Ontario",
    "Orange",
    "Oroville",
    "Oxnard",
    "Pacific Grove",
    "Palm Springs",
    "Palmdale",
    "Palo Alto",
    "Pasadena",
    "Petaluma",
    "Pomona",
    "Port Hueneme",
    "Rancho Cucamonga",
    "Red Bluff",
    "Redding",
    "Redlands",
    "Redondo Beach",
    "Redwood City",
    "Richmond",
    "Riverside",
    "Roseville",
    "Sacramento",
    "Salinas",
    "San Bernardino",
    "San Clemente",
    "San Diego",
    "San Fernando",
    "San Francisco",
    "San Gabriel",
    "San Jose",
    "San Juan Capistrano",
    "San Leandro",
    "San Luis Obispo",
    "San Marino",
    "San Mateo",
    "San Pedro",
    "San Rafael",
    "San Simeon",
    "Santa Ana",
    "Santa Barbara",
    "Santa Clara",
    "Santa Clarita",
    "Santa Cruz",
    "Santa Monica",
    "Santa Rosa",
    "Sausalito",
    "Simi Valley",
    "Sonoma",
    "South San Francisco",
    "Stockton",
    "Sunnyvale",
    "Susanville",
    "Thousand Oaks",
    "Torrance",
    "Turlock",
    "Ukiah",
    "Vallejo",
    "Ventura",
    "Victorville",
    "Visalia",
    "Walnut Creek",
    "Watts",
    "West Covina",
    "Whittier",
    "Woodland",
    "Yorba Linda",
    "Yuba City"
)
