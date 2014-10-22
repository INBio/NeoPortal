'''
    File name: read-files.py
    Author: Arturo Vargas 
    Date created: 9/09/2014
    Date last modified: 9/09/2014

    Return the path and file name for Staxon.csv or DarwinCore.csv
'''

import sys
from os import listdir
from os.path import isfile, join

taxonString = "STaxon"
dwcString = "DarwinCore"
try:
  mypath = sys.argv[1]
  type = sys.argv[2]
except IndexError:
  print 'Need two arguments'
  sys.exit(1)

if type == '-t':
  stringFind = taxonString
elif type == '-d':
  stringFind = dwcString
else:
  print 'Second arg -t or -d'
  sys.exit(1)

if not mypath.endswith('/'):
    mypath += '/'

onlyfiles = [ f for f in listdir(mypath) if isfile(join(mypath,f)) ]

for file in onlyfiles:
  if stringFind in file:
    print mypath + file
    sys.exit(0)

