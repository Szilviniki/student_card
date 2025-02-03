namespace student_card.Services
{
    public interface IOrientationService
    {
        void SetOrientationLandscape();
        void SetOrientationPortrait();
        void ResetOrientation();
    }
}