//  ActionContext.h
#import <Cordova/CDV.h>

@interface NativeDialog : CDVPlugin <UIActionSheetDelegate> {
}

#pragma mark - Properties

@property (nonatomic, copy) NSString* callbackId;

#pragma mark - Instance methods

- (void)create:(CDVInvokedUrlCommand*)command;

@end
