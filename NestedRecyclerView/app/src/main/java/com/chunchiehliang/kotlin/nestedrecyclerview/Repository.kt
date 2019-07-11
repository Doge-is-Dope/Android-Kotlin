package com.chunchiehliang.kotlin.nestedrecyclerview

object Repository {
    fun createDummyMovies(): List<Movie> {
        val movieList = mutableListOf<Movie>()
        movieList.add(
            Movie(
                id = 0,
                title = "M0",
                genres = listOf(
                    Genre(28, "G0"),
                    Genre(12, "G1"),
                    Genre(16, "G2"),
                    Genre(37, "G3")
                )
            )
        )

        movieList.add(
            Movie(
                id = 1,
                title = "M1",
                genres = listOf(
                    Genre(53, "G0"),
                    Genre(37, "G3")
                )
            )
        )

        movieList.add(
            Movie(
                id = 2,
                title = "M2",
                genres = listOf(
                    Genre(878, "G0"),
                    Genre(36, "G3")
                )
            )
        )


        movieList.add(
            Movie(
                id = 3,
                title = "M3",
                genres = listOf(
                    Genre(10752, "G0"),
                    Genre(14, "G1")
                )
            )
        )

        movieList.add(
            Movie(
                id = 4,
                title = "M4",
                genres = listOf(
                    Genre(10571, "G1"),
                    Genre(10752, "G2")
                )
            )
        )

        movieList.add(
            Movie(
                id = 5,
                title = "M5",
                genres = listOf(
                    Genre(9648, "G6"),
                    Genre(99, "G7")
                )
            )
        )

        movieList.add(
            Movie(
                id = 6,
                title = "M6",
                genres = listOf(
                    Genre(0, "G0"),
                    Genre(14, "G4")
                )
            )
        )

        return movieList
    }
}