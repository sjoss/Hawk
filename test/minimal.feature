Feature: web 1

  Scenario: try to login to github
  and then do a google search

    Given url 'http://0.0.0.0:8080/users/185203217'
    When method get
    Then status 200
    And match response == {"id":185203217,"screenname":"Lilicia_Onechan","tweets":29,"favorites":8,"hashTags":27,"mentions":0}