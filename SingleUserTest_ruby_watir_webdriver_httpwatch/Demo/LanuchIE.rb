#-------------------------------------------------------------# 
# Demo test for the Watir controller. 
# 
# Launch IE browser. 
#-------------------------------------------------------------# 

require 'rubygems'  
require 'watir'  
require 'watir-classic'   
  
ie = Watir::Browser.new  
ie.goto("http://www.baidu.com") 
ie.close