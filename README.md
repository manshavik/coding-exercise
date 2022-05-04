GET -> To get investor details along with the gund and holding details
http://localhost:8080/investor/100

PUT -> To insert or update investor details along with the fundList which is optional
http://localhost:8080/investor
{
	"name":"Ravi"
}

PUT -> To update investor id (parentId) with new fund id ()childId
http://localhost:8080/investor/updatefund
{
	"parentId":100,
	"childId":1002
}


GET -> Get the market value of the investor
http://localhost:8080/investor/marketvalue/101?excludedHoldings=10000




GET -> Get details of the fund along with thier holding details
http://localhost:8080/fund/1000

PUT -> To insert or update fund details along with the holdingList which is optional
http://localhost:8080/fund
{
	"name":"HDFC"
}

PUT -> To update the fundId and holdingId and their quantity
http://localhost:8080/fund/updateholding
{
	"id":{
		"fundId":1001,
		"holdingId":10000
	},
		"fundDetails":{
			"id":1001,
			"name":"test"
		},
		"holdingDetails":{
			"id":10000,
			"name":"value"
		},
		"quantity":23
}

GET -> To get market value of the fund
http://localhost:8080/fund/marketvalue/1000?excludeHoldings=1000



GET -> Get the holding details
http://localhost:8080/holding/100

PUT -> To insert or update the holding details
http://localhost:8080/holding
{
	"name":"HAL",
	"price":20
}
