using student_card.Services;
using Microsoft.Maui.Platform;

namespace student_card.Views;

public partial class LoginView : ContentPage
{
	
	public LoginView()
	{
		InitializeComponent();

#if ANDROID
        Microsoft.Maui.Handlers.EntryHandler.Mapper.AppendToMapping(nameof(Entry),
                (handler, view) => { 
               
                 handler.PlatformView.BackgroundTintList = 
                    Android.Content.Res.ColorStateList.ValueOf(Colors.Transparent.ToPlatform());
                });
#endif
        // _orientationService = DependencyService.Get<IOrientationService>();
    }

    //protected override void OnAppearing()
    //{
    //    base.OnAppearing();
    //    _orientationService.SetOrientationPortrait(); 
    //}

    //protected override void OnDisappearing()
    //{
    //    base.OnDisappearing();
    //    _orientationService.ResetOrientation();
    //}
}