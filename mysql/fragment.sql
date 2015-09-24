CREATE TABLE `app_resource_feedback` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `resourceId` INT(11) NOT NULL DEFAULT '0' COMMENT '资源id',
  `userId` INT(11) NOT NULL DEFAULT '0' COMMENT '用户id，哪个用户点击',
  `scoreType` TINYINT(1) NOT NULL DEFAULT '0'  COMMENT '得分类型，0为一般，1为有用',
  `resourceType` TINYINT(1) NOT NULL DEFAULT '0'  COMMENT '资源类型，养育建议为1，关键期视频为2，亲子游戏为3，通用课程为4',
  PRIMARY KEY  (`id`) 
) ENGINE=MYISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

ALTER TABLE `abc`.`app_resource_feedback`
  ADD COLUMN `createDate` DATE DEFAULT CURRENT_DATE()   NOT NULL  COMMENT '创建时间';

ALTER TABLE `app_resource_feedback` ADD createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() 


ALTER TABLE `app_resource_everyday` DROP COLUMN answerrecordId;

ALTER TABLE app_resource_everyday MODIFY COLUMN resourceType INT(1);

  
  

-- 加序号以及日期往后加3天
 SELECT sid,'',209315,DATE_ADD('2015-09-16', INTERVAL 3 DAY) fdate,'3' FROM (
 SELECT @rownum:=@rownum+1 rownum,sid FROM 
 (SELECT @rownum:=0,s.id AS sid
    FROM Answerrecommendgames s LEFT JOIN Interactivegame g ON s.`GameID`=g.`id`  WHERE s.`answerRecordID`=209315 ORDER BY g.`id` ASC LIMIT 3) t ) t1
  WHERE   rownum=3;
  
  -- mysql 字符串连接函数CONCAT
    SELECT g.`game_name` title,g.`content` content,CONCAT('http://abc.yaolan.com/images/class/games/',SUBSTRING_INDEX(g.`ImageUrl`,'.',1),'_196_146.jpg') imgUrl,g.`GameType`
    FROM  Interactivegame g WHERE g.`id`=1 LIMIT 1
    
  -- 字符截取函数
    SELECT SUBSTRING_INDEX('123.jpg','.',1)
  
  -- mysql截取函数 SUBSTRING
  UPDATE app_resource_everyday_learning g SET g.`imagename`=SUBSTRING(g.`imagename`,1,(LENGTH(g.`imagename`)-7))
  
  
  -- mysql随机数
     SELECT RAND()
     
  

