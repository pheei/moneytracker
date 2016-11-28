# MoneyTracker
### Project Description
This project has been developed as REST web API with Spring boot, consuming the data from the GetAllTransaction endpoint and determines user's monthly income and spent. The basic features are as follows.

  - Loads a user's transactions from the GetAllTransactions endpoint
  - Determines how much money the user spends and makes in each of the months for which we have data, and in the "average" month.

Three additional features have also been implemented.

  - **ignore-donuts**: remove all the donuts related transactions that have merchant name “Krispy Kreme Donuts” or “DUNKIN #336784” from the output transaction list.
  - **crystal-ball**: fetch current month's prediction from API endpoint, and append the predicted transactions into the original transaction list.
  - **ignore-cc-payments**: remove all the credit card transactions from the original list.

The application will output result in following JSON format.
```sh

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
```
### Project Instruction
##### How to build?
First, download the project files from the github repository using git clone command, and then nagivate to the project directory:
```sh
$ git clone https://github.com/pheei/moneytracker.git
$ cd moneytracker
```
build the project with Maven mvn command, and then execute the generated .jar file
```sh
$ mvn package
$ java -jar target/moneytracker-1.0-SNAPSHOT.jar
```
Now, the REST web service will be running on your local machine at port 8080. Try to enter follwing URL into your web browser. If a simple welcome message shows up, it indicates the web service is running correctly.
```sh
http://localhost:8080/
```
##### How to use?
If you want to get all the transactions without any additional features, you can simply enter the following URL in the web browser.
```sh
http://localhost:8080/transaction/all
```
And then, will will get a output like this in JSON format.
```sh
{
  "result" : {
    "All_Transactions" : {
      "2014-10" : {
        "spent" : "$1578.44",
        "income" : "$3429.79"
      },
      ...
      "2016-11" : {
        "spent" : "$4694.38",
        "income" : "$3441.83"
      },
      "average" : {
        "spent" : "$3472.47",
        "income" : "$3450.46"
      }
    }
  }
}
```
Or if you want to add predited transactions of currently month into the original list, you can enter:
```sh
http://localhost:8080/transaction/predict
```
Then, you will notice that the spent of the last month and the average spent will be increased as expected.
```sh
{
  "result" : {
    "All_Transactions" : {
      ...
      "2016-11" : {
        "spent" : "$5147.76",
        "income" : "$3441.83"
      },
      "average" : {
        "spent" : "$3489.91",
        "income" : "$3450.46"
      }
    }
  }
}
```
Besides, you can also remove credit card payment or donuts related transactions, by simplying adding "no_donut" and "no_credit" parameters to the end of URL. And they will be set to "false" by defaut, if you don't explicitly add them.
```sh
http://localhost:8080/transaction/all?no_donut=false&no_credit=false
http://localhost:8080/transaction/predict?no_donut=false&no_credit=false
```
If you want to get all the transaction without donuts and credit card payment, you can enter the URL as follows.
```sh
http://localhost:8080/transaction/all?no_donut=true&no_credit=true
```
And once the "no_credit" is set to true, the application will automatically generate an extra list to display all the removed credit card transactions for verification purpose. The result will be presented in following format.
```sh
{
  "resultWithoutCreditPayment" : {
    "All_Transactions" : {
      "2014-10" : {
        "spent" : "$1478.21",
        "income" : "$3429.79"
      },
      ...
      "average" : {
        "spent" : "$3076.22",
        "income" : "$3110.82"
      }
    },
    "Removed_Transactions" : [ {
      "_pending" : false,
      "aggregation-time" : 1415048280000,
      "transaction-id" : "1415193660000",
      "account-id" : "nonce:comfy-checking/hdhehe",
      "raw-merchant" : "CC PAYMENT",
      "merchant" : "CC Payment",
      "is-pending" : false,
      "transaction-time" : "2014-11-03T20:58:00.000Z",
      "amount" : -5194500,
      "previous-transaction-id" : null,
      "categorization" : "Unknown",
      "memo-only-for-testing" : null,
      "payee-name-only-for-testing" : null,
      "clear-date" : 1415193660000
    }, 
    ...
    {
      "_pending" : false,
      "aggregation-time" : 1475493617561,
      "transaction-id" : "1475459040000",
      "account-id" : "nonce:comfy-cc/hdhehe",
      "raw-merchant" : "CREDIT CARD PAYMENT",
      "merchant" : "Credit Card Payment",
      "is-pending" : false,
      "transaction-time" : "2016-10-03T00:00:00.000Z",
      "amount" : 5194500,
      "previous-transaction-id" : null,
      "categorization" : "Credit Card Payment",
      "memo-only-for-testing" : "Example Memo",
      "payee-name-only-for-testing" : "CREDIT CARD PAYMENT",
      "clear-date" : 1475459040000
    } ]
  }
}
```

##### P.S.
All the required features in the excersice instructions document has been implemented. Since the excersice doesn't explicity requires UI design and front-end development, I just choose to build it in a more straight forward way as plain REST web API without fancy client side or data rendering technologies. If a front-end part is still required, please let me know, and I will continue my development.
