select t1.trade_no tradeNo, 
	t2.unit_ou_code unitOuCode, 
	t2.unit_name unitName, 
	t2.account_no accountNo, 
	t2.account_name accountName, 
	t3.nature_name natureName, 
	t2.currency_code currencyCode, 
	t2.currency_name currencyName, 
	t1.upper_source_code upperSourceCode, 
	t1.upper_source_name upperSourceName, 
	t1.amount amount, 
	t1.create_time createTime, 
	t1.write_date writeDate, 
	t1.interest_date interestDate, 
	t1.direction_code directionCode, 
	t1.direction_name directionName, 
	t1.leftover_amount leftoverAmount, 
	t1.memo memo, 
	t1.make_user_name makeUserName, 
	t1.check_user_name checkUserName 
from dual;