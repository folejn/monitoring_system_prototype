import os
import time
import RPi.GPIO as GPIO
from PCA9685 import PCA9685


class Driver:
    def __init__(self) -> None:
        self.pwm = PCA9685()
        self.pwm.setPWMFreq(50)


        self.max_PAN      = 180
        self.max_TILT     = 145
        self.min_PAN      = 0
        self.min_TILT     = 0
            
        self.STEP_PAN     = 10
        self.STEP_TILT    = 10
        self.current_PAN  = 90
        self.current_TILT = 90
        self.pwm.setRotationAngle(1, self.current_PAN)     
        self.pwm.setRotationAngle(0, self.current_TILT) 

    def up(self):
        self.current_PAN = max(self.min_PAN, self.current_PAN - self.STEP_PAN)
        self.pwm.setRotationAngle(1, self.current_PAN) 
        time.sleep(0.001)

    def down(self):
        self.current_PAN = min(self.max_PAN, self.current_PAN + self.STEP_PAN)
        self.pwm.setRotationAngle(1, self.current_PAN) 
        time.sleep(0.001)

    def right(self):
        self.current_TILT = max(self.min_TILT, self.current_TILT - self.STEP_TILT)
        self.pwm.setRotationAngle(0, self.current_TILT) 
        time.sleep(0.001)

    def left(self):
        self.current_TILT = min(self.max_TILT, self.current_TILT + self.STEP_TILT)
        self.pwm.setRotationAngle(0, self.current_TILT) 
        time.sleep(0.001)

    def destruct(self):
        self.pwm.exit_PCA9685()