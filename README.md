# CompuCom_2
<p>Java Web Application developed with Spring Framework </p>
<p>To run it, you have to:</p>
<p>
  <ul>
    <li>Update the <i>pom.xml</i> with a <code>$ mvn clean install</code> since console to download the dependencies of the project.</li>
    <li>Restore the MySQL Database file called <b>bckup_DDMMAAAA.mysql</b> (whose already contains products and users).
        It will create a new schema called <b>compucom</b>.
    </li>
    <li>Change somevalues if necessary at <b>application.yml</b> in Compucom2(Or project name) > src > main > resources > application.yml</li>
    <ul>
      <li>Set the correct username and password of your MySQL Configuration.</li>
      <li>Check the url is correct.</li>
      <li>You can change the port of the app.</li>
    </ul>    
  </ul>
</p>
<p>
  Now you can run the Web application since your browser at http://localhost:9000 (Or Port established).
</p>

