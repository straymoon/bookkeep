billUserRelation
===
SELECT
	`user`.`name` 
FROM
	bill_user
	LEFT JOIN `user` ON bill_user.user_id = `user`.id AND `user`.`status` =1
	LEFT JOIN bill ON bill_user.bill_id = bill.id AND bill.`status` =1
WHERE
	1=1
	@if(!isEmpty(billId)){
	and bill.id = #billId#
	@}
