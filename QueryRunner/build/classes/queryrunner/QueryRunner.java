����   4
 J � �
  �	 * �	 * � �
  �	 * � �	 * � �	 * � � �
  �
  � � � � � � � � � �
  �
  �
  �
  �
  �
  �
  �
  �
  �
  �
  �
  �
  �
  �
  �
  � �
 * � �	 � �
 , �	 � � �
 � �
 , � �
 9 � �
 5 �
 � � � � � � �
 * � � � � � �
 , �
 * � �
 * �
 * �
 * � � �
 9 �
 � � � � �
 � �
 * � InnerClasses 
m_jdbcData Lqueryrunner/QueryJDBC; m_error Ljava/lang/String; m_projectTeamApplication m_queryArray Ljava/util/ArrayList; 	Signature .Ljava/util/ArrayList<Lqueryrunner/QueryData;>; m_updateAmount I <init> ()V Code LineNumberTable LocalVariableTable this Lqueryrunner/QueryRunner; GetTotalQueries ()I GetParameterAmtForQuery (I)I queryChoice e Lqueryrunner/QueryData; GetParamText (II)Ljava/lang/String; parmnum GetQueryText (I)Ljava/lang/String; GetUpdateAmount GetQueryHeaders ()[Ljava/lang/String; GetQueryData ()[[Ljava/lang/String; GetProjectTeamApplication ()Ljava/lang/String; isActionQuery (I)Z isParameterQuery ExecuteQuery (I[Ljava/lang/String;)Z parms [Ljava/lang/String; bOK Z ExecuteUpdate Connect K(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z szHost szUser szPass 
szDatabase bConnect StackMapTable 
Disconnect ()Z GetError main ([Ljava/lang/String;)V k j header 	queryData [[Ljava/lang/String; test sc Ljava/util/Scanner; choice args queryrunner user 	userInput � � � ~ � 
SourceFile QueryRunner.java ^ _ queryrunner/QueryJDBC S T \ ] java/util/ArrayList X Y   U V 	InfoCOVID W V queryrunner/QueryData OSELECT PATIENT_ID, COVID_SEVERITY FROM PATIENTS WHERE COVID_SEVERITY = "SEVERE" ^ � � � WSELECT PATIENT_ID, COVID_SEVERITY, HEALTH_ISSUES FROM PATIENTS WHERE HEALTH_ISSUES >= 2 >SELECT STATE_NAME, DEATH_NO FROM STATES WHERE DEATH_NO > 20000 =SELECT STATE_NAME, CASE_NO FROM STATES WHERE CASE_NO > 100000 >SELECT STATE_NAME, DEATH_NO FROM STATES ORDER BY DEATH_NO DESC KSELECT COVID_SEVERITY, count(*) FROM PATIENTS WHERE COVID_SEVERITY = "MILD" vSELECT STATES.STATE_NAME, CASES.CASE_NO FROM STATES, CASES WHERE STATES.CASE_NO = CASES.CASE_NO ORDER BY CASES.CASE_NO sSELECT PATIENT_RACE, COUNT(*) FROM PATIENTS WHERE COVID_SEVERITY = "SEVERE" GROUP BY PATIENT_RACE ORDER BY COUNT(*) JSELECT COVID_SEVERITY, DEATH_NO FROM COVID_SEVERITY ORDER BY DEATH_NO DESC TSELECT STATE_NAME, COUNT(*) FROM PATIENTS GROUP BY STATE_NAME ORDER BY COUNT(*) DESC � f � � � f l p � w � s � u � � � �  { � f � � w � queryrunner/QueryRunner java/util/Scanner	 ^
 BPlease choose which applications: 'c' for console or 'g' for GUI:  � w g � queryrunner/QueryRunner$1 ^ c java/lang/String cs100 mm_cpsc502101team07 mm_cpsc502101team07Pass- � � 7Please enter number of queries (1 ~ 10) or -1 to exit:  ]1: Patients with Severe Cases
2: Severe Cases with Health Issues
3: States with >20,000deaths U4: States with > 100,000 cases
5: State with greatest deaths 
6: Severity in patients h7: State with least number of cases 
8: Patient severity based on race 
9: Severity correlating to death 10: Patients in states f e f !Invalid choices, please re-enter: { | r s t u %-10s 
 java/lang/Object w %-13s %-10s
 %-10s %-10s %-10s
 %-15s _ � � (Ljava/lang/String;)V add (Ljava/lang/Object;)Z size get (I)Ljava/lang/Object; GetParmAmount GetQueryString 
GetHeaders GetData IsQueryAction IsQueryParm GetAllLikeParams ()[Z *(Ljava/lang/String;[Ljava/lang/String;[Z)Z ((Ljava/lang/String;[Ljava/lang/String;)Z GetUpdateCount ConnectToDatabase CloseDatabase java/lang/System in Ljava/io/InputStream; (Ljava/io/InputStream;)V out Ljava/io/PrintStream; java/io/PrintStream println nextLine equals (Lqueryrunner/QueryRunner;)V java/awt/EventQueue invokeLater (Ljava/lang/Runnable;)V nextInt toString printf <(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream; ! * J     S T    U V    W V    X Y  Z    [  \ ]     ^ _  `  @     �*� *� Y� � *� *� Y� � *	� 
*� *� � Y� � W*� � Y� � W*� � Y� � W*� � Y� � W*� � Y� � W*� � Y� � W*� � Y� � W*� � Y� � W*� � Y� � W*� � Y� � W�    a   F             % ! + $ < % M & ^ ' o ( � ) � * � + � , � - � . b       � c d    e f  `   2     *� � �    a       2 b        c d    g h  `   S     *� � � M,� �    a   
    7  8 b         c d      i ]    j k   l m  `   ^     *� � � N-� �    a   
    =  > b   *     c d      i ]     n ]    j k   o p  `   S     *� � � M,� �    a   
    C  D b         c d      i ]    j k   q f  `   /     *� �    a       O b        c d    r s  `   2     *� � �    a       X b        c d    t u  `   2     *� �  �    a       d b        c d    v w  `   /     *� �    a       i b        c d    x y  `   S     *� � � M,� !�    a   
    m  n b         c d      i ]    j k   z y  `   S     *� � � M,� "�    a   
    s  t b         c d      i ]    j k   { |  `   �     $>*� � � :*� � ,� #� $>�    a       z  {  | " } b   4    $ c d     $ i ]    $ } ~   "  �    j k   � |  `   �     *>*� � � :*� � ,� %>**� � &� �    a       �  �  �  � ( � b   4    * c d     * i ]    * } ~   (  �    j k   � �  `   �     !*� +,-� '6� **� � (� 
�    a       �  �  �  � b   >    ! c d     ! � V    ! � V    ! � V    ! � V    � �  �    �   � �  `   e     *� � )<� **� � (� 
�    a       �  �  �  � b        c d     � �  �    �   � w  `   /     *� 
�    a       � b        c d   	 � �  `  �    �� *Y� +L� ,Y� -� .M� /0� 1,� 2N-3� 4� � 5Y+� 6� 7��-8� 4��� 9:+:;<;� =W� ,Y� -� .:� />� 1� /?� 1� /@� 1� /A� 1� /B� 1� C6+� D� � /E� 1� C6����F+d� FW+� G:+� H:6		�� �6

	2�� �	� &
� !�� � /I� JY2� KS� LW	� 0
� +�� $� /M� JY2� KSY2� KS� LW	� :
� 5�� .� /N� JY2� KSY2� KSY2� KS� LW� /O� JY	2
2S� LW�
��D� /� P�	��-� /� P� />� 1� /?� 1� /@� 1� /A� 1� /B� 1� C6+� D� � /E� 1� C6�����+� QW�    a   � 0   �  �  �  �   � ) � 7 � @ � F � S � _ � g � o � w �  � � � � � � � � � � � � � � � � � � � � � � � � � � �9 �J �u �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��  b   p  � � � ] 
 � � � ] 	 �/ � ~  �) � �  F� � ~  _� � �  �i � ]   � � ~   � � d  � � �   � � V  �   8 � 7 � � �� V � ��  � �� 
21;� � 4� �   �    � R   
  5     