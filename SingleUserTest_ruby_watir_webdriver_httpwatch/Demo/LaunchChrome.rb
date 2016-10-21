#-------------------------------------------------------------# 
# Demo test for the Watir controller. 
# 
# Use Chrome browser. 
#-------------------------------------------------------------# 

#launch Chrome
require 'watir'  
browser = Watir::Browser.new :chrome  
browser.goto("http://www.baidu.com")  