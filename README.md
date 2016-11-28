This project has been developed as REST web API with Spring boot, which consumes the data from the GetAllTransaction endpoint and determines users monthly income and spent.

The data will be returned and displayed in JSON format according to corresponding HTTP requests. Result will be shown in following format. All the three required features, "ignore donuts", "crystal ball", and "ignore cc payment", have been implemented, and can be reached by corresponding URI.


"All_Transactions" : {
    "2014-10" : {
        "spent" : "$1578.44",
        "income" : "$3429.79"
    },
    ......
    "2016-11" : {
        "spent" : "$4694.38",
        "income" : "$3441.83"
    },
    "average" : {
        "spent" : "$3472.47",
        "income" : "$3450.46"
    }
}


