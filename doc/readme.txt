Notes about Libra-Monitor
=========================

inside settings.xml this server was added to <server> section in order maven to be able to connect to aws :
    <server>
        <id>ec2-node</id>                       
        <username>ec2-user</username>         
        <privateKey>/Users/Filipe/.ssh/Libra.pem</privateKey>         
    </server> 
    
command to launch maven : 
	mvn clean install

command to run Libra on aws :
	 nohup java -jar libra-monitor-0.0.1-SNAPSHOT.jar &
	 
--

Connect to AWS via the terminal : ssh -i /Users/Filipe/.ssh/Libra.pem ec2-user@54.202.68.108