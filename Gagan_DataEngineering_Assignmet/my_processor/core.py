from .utils import clean_member_data, InvalidMemberDataError


class Member:
    """Represents a single member."""

    def __init__(self, name, email, phone):
        self.name = name
        self.email = email
        self.phone = phone

    def __str__(self):
        return (
            f"Member(name='{self.name}', "
            f"email='{self.email}', "
            f"phone='{self.phone}')"
        )

    def __repr__(self):
        return self.__str__()


class MemberDataProcessor:
    """Processes and manages member data."""

    def __init__(self, raw_members):
        self.raw_members = raw_members
        self.members = []

    def process_members(self):
        """Clean and validate all raw member data."""

        for data in self.raw_members:

            name = data.get("name", "Unknown")

            print(f"Processing member: {name}...", end=" ")

            try:
                cleaned_data = clean_member_data(data)

                member = Member(
                    cleaned_data["name"],
                    cleaned_data["email"],
                    cleaned_data["phone"]
                )

                self.members.append(member)

                print("Validation Successful.")

            except InvalidMemberDataError as error:
                print(f"\nError: {error} Skipping.")

            except (TypeError, ValueError) as error:
                print(f"\nError: {error} Skipping.")

        return self.members

    def filter_members_by_name(self, name):
        """Filter members using lambda and filter()."""

        return list(
            filter(
                lambda member: member.name == name,
                self.members
            )
        )

    def get_all_members(self):
        """Return all successfully processed members."""

        return self.members

    def get_member_count(self):
        """Return number of successfully processed members."""

        return len(self.members)