import SocketServer
import socket

class MyHandler(SocketServer.BaseRequestHandler):

	def handle(self):
		self.oracion= self.request.recv(1024).strip()
		self.num=len(self.oracion)
		print self.oracion
		self.request.send(self.oracion)
	
def main():
	  
	print "Socket Python" 
	host=socket.gethostname()
	print "TU DIRECCION IP ES:",socket.gethostbyname_ex(socket.gethostname()) 
	port=9999
	
	
	server1=SocketServer.TCPServer((host,port),MyHandler)
	print "Servidor Corriendo"
	server1.serve_forever()
	
main()
	 
	
