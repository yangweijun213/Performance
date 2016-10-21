#-------------------------------------------------------------# 
# Demo test for the Watir controller. 
# 
# Use Firefox browser. 
#-------------------------------------------------------------# 


#method1: launch Firefox
require 'watir'  
browser = Watir::Browser.new :firefox  
browser.goto("http://www.baidu.com")  
browser.close

#method2: Launch Firefox

#require 'watir-webdriver'
#browser = Watir::Browser.new
#browser.goto('http://www.baidu.com/')
#browser.close
