# IS3261

## There are two DB

1. Food
- ID - 'F1', 'F2', ..., 'F11' 
- name
- price
- priceDisscount
- priceIncrease
- calorie
- category - 'vege', 'meat', 'mix' for healthy food, 'unhealthy' for unhealthy food
- location
- ingredient1
- ingrefient2
- ingredient3
- ingredient4
- currStatus - to keep track whether to discount (-1), normal (0) or increase price (1)

2. Transaction
keep track both food history and top up amount
- dateTime
- name
- amount
- status (not for topup) - the status when the food history recorded
- ID (not for topup)


## Discover

1. discounted just for you
- will pick food with currStatus == -1
- if still less than 3, will pick from healthy food

2. popular food
- will pick from healthy food

#### TODO
- food image - need to set image from fragment
- discover more food in NUS, maybe can create an activity with scrollview to show all food?
- view more bottom fragment
- search bar

## History

#### TODO
- food image
- view more bottom fragment

## Scan
1. will check if sufficient balance
2. after payment will update currStatus of foodDB
- if no vege food is consumed from last 4 food, vege food currStatus==-1
- if no meat food is consumed from last 4 food, meat food currStatus==-1
- if healthy food is consumed more than 3 times from last 4 food, currStatus==1
- if unhealthy food is consumed more than 2 times from last 4 food, currStatus==1


#### TODO
- food image


## History
- attempted to use dialog for topup but couldnt figure out so i use activity

## Account

## TODO
- link to internet